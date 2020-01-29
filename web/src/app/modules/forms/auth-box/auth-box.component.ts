import { Component, OnInit, Input, Output, ViewEncapsulation, EventEmitter, ViewChild } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { FormBasicComponent } from '../form-basic/form-basic.component';
import { AuthApiManager } from 'src/app/auth.api.manager';
import { ApiProxyComponent } from '../../common/api-proxy/api-proxy.component';
import { SakuraEventBasic } from 'src/app/models/utility/event.basic';
import { Hashmap } from 'src/app/models/data-structure/hashmap.model';

@Component({
  selector: 'sakura-auth-box',
  templateUrl: './auth-box.component.html',
  styleUrls: ['./auth-box.component.scss'],
  encapsulation: ViewEncapsulation.ShadowDom
})
export class AuthBoxComponent extends FormBasicComponent implements OnInit {

  @Input() formHeaderText: String;
  @Input() submitButtonText: String;
  @Input() createAccountPrompt: String;
  @Input() createAccountHyperLinkText: String;
  @Input() createAccountHyperLinkLink: String;

  @ViewChild(ApiProxyComponent) apiProxy: ApiProxyComponent;

  constructor(formBuilder: FormBuilder, private apiService: AuthApiManager) {
    super(formBuilder);
    this.formGroupConfiguration = {
      username: '',
      password: ''
    };
  }

  onSubmit(formData) {
    console.log(formData);
    this.apiProxy.addEvent(new SakuraEventBasic("register"));
    this.apiProxy.invoke('register', () => {
      console.log('got data');
    })
  }

  ngOnInit() {
    super.ngOnInit();
    this.apiProxy.bind(this.apiService.setDataType('form'));
  }

}
