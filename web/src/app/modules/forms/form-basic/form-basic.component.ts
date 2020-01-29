import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'sakura-form-basic',
  templateUrl: './form-basic.component.html',
  styleUrls: ['./form-basic.component.scss'],
  encapsulation: ViewEncapsulation.ShadowDom
})
export class FormBasicComponent implements OnInit {

  protected formGroupConfiguration = {};
  protected formGroup = {};

  constructor(private formBuilder: FormBuilder) {}

  gatherFormFields() {}

  initFormGroup() {
    this.formGroup = this.formBuilder.group(this.formGroupConfiguration);
  }

  onSubmit(formData) {
    console.log('basic form submitted');
  }

  ngOnInit() {
    this.initFormGroup();
  }

}
