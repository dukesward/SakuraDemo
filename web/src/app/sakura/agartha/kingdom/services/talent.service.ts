import { KingdomActionHandler } from './../mainframe/services/kingdom.action.handler';
import { Injectable } from '@angular/core';
import { KingdomContentService } from './kingdom.content.service';
import { EntityBasic } from 'src/app/models/entity.basic';

@Injectable({
  providedIn: 'root'
})
export class TalentService extends KingdomContentService {

  constructor(protected actionHandler: KingdomActionHandler) {
    super(actionHandler);
  }

  handle(data: any): EntityBasic[] {
    var talents = [this.emptyItem("talent")];
    console.log(data);
    for(var talent in data.talents) {
    }
    return talents;
  }
}
