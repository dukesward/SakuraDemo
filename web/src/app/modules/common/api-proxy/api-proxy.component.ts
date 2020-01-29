import { Component, OnInit } from '@angular/core';
import { SakuraApiManager } from 'src/app/sakura.api.manager';
import { utils } from '../../../common-utils';
import { Stack } from 'src/app/models/data-structure/stack';
import { Hashmap } from 'src/app/models/data-structure/hashmap.model';
import { SakuraEventBasic } from 'src/app/models/utility/event.basic';
import { TypedList } from 'src/app/models/data-structure/typed-list.model';

@Component({
  selector: 'api-proxy',
  templateUrl: './api-proxy.component.html',
  styleUrls: [
    './api-proxy.component.scss',
    '../../../sakura/agartha/kingdom/kingdom.theme.scss',
    '../../../sakura/sakura.theme.scss'
  ]
})
export class ApiProxyComponent implements OnInit {

  private events: Stack<SakuraEventBasic>;
  private lastEvent: SakuraEventBasic;
  private service: SakuraApiManager;
  private apiError: object;
  private errorHandler: CallableFunction;
  private loading: boolean;

  constructor() {
    this.apiError = {};
    this.events = new Stack<SakuraEventBasic>();
    this.loading = false;
  }

  ngOnInit() {
  }

  bind(service: SakuraApiManager) {
    this.service = service;
    return this;
  }

  setEvents(events: Hashmap<SakuraEventBasic>) {
    var self = this;
    events.iterate((key, event) => {
      self.events.add(event);
    });
  }

  addEvent(event: SakuraEventBasic) {
    this.events.add(event);
  }

  attachEvent(event: SakuraEventBasic) {
    this.events.attach(event);
    return this;
  }

  attachEvents(events: SakuraEventBasic[]) {
    this.events.addAll(events);
    return this;
  }

  error(handler: CallableFunction) {
    this.errorHandler = handler;
    return this;
  }

  hasError(): boolean {
    return this.apiError && this.apiError['message'];
  }

  finish(event: SakuraEventBasic, data: any) {
    if(!event.completed(data, event.clock)) {
      this.apiError = this.apiError || {};
      this.apiError['message'] = "Get data from " + event.eventId + " failed";
    }
    event.finish(data);
  }

  transaction(events: TypedList<SakuraEventBasic>) {
    // A special type of chained calls which works in such a way
    // that when any one of the calls fails, the proxy stops calling
    // the rest and sends a rollback request to all succeeded updates.
    events.each(function() {

    });
  }

  invokeNow(event: SakuraEventBasic, srcId: string, callback: () => void) {
    this.addEvent(event);
    this.invoke(srcId, callback);
  }

  invoke(srcId: string, callback: () => void) {
    var srcFull = 'do' + utils.capitalize(srcId);
    // console.log('source name is ' + srcFull);
    if(this.events.hasItem()) {
      var event = this.events.next();
      this.lastEvent = event;
      var s = this.service[srcFull];
      if(s) {
        this.loading = true;
        s.call(this.service, event).subscribe(
          data => {
            this.loading = false;
            if(data.error) {
              this.apiError = this.errorHandler ? this.errorHandler(data) : data.message;
            }else {
              if(!event.completed(data.body, 2)) {
                event.tick();
                this.events.add(this.lastEvent);
              }
              this.finish(event, data.body);
              this.invoke(srcId, callback);
            }
          },
          error => {
            console.log(error);
            this.loading = false;
            this.apiError = error.error || {};
            if(!this.apiError['message']) {
              this.apiError['message'] = error.statusText;
            }
            /*if(error.ok) {
              this.apiError = error.error;
            }else {
              this.apiError = {
                status: error.status,
                message: error.statusText
              }
            }*/
          },
        );
      }
    }else {
      if(callback) callback();
    }
  }

}
