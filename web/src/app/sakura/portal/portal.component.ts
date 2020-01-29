import { Component, OnInit, ViewChild } from '@angular/core';
import { AuthBoxComponent } from 'src/app/modules/forms/auth-box/auth-box.component';
import { ActivatedRoute, Router } from "@angular/router";
import { StringList } from 'src/app/models/data-structure/string-list.model';

@Component({
  selector: 'app-portal',
  templateUrl: './portal.component.html',
  styleUrls: [
    '../sakura.theme.scss',
    './portal.component.scss'
  ]
})
export class PortalComponent implements OnInit {

  private params: string[];
  private routePath: string;

  @ViewChild(AuthBoxComponent) authBoxChild;
  portalStaticConfig = {
    authBoxSignIn: {
      formHeaderText: 'Sign in to Kingdom',
      submitButtonText: 'Sign in',
      hasPromptBox: true,
      createAccountPrompt: 'New to Kingdom?',
      createAccountHyperLinkText: 'Create an account now',
      createAccountHyperLinkLink: '/sakura/signup'
    },
    authBoxSignUp: {
      formHeaderText: 'Create your account',
      submitButtonText: 'Sign up',
      createAccountPrompt: 'New to Kingdom?',
      hasPromptBox: false
    }
  }

  constructor(public route: ActivatedRoute, public router: Router) {
  }

  ngOnInit() {
    console.log('portal is running');
    this.params = new StringList(this.router.url.split('/')).removeEmpty().toArray();
    this.routePath = this.params[1] || 'root';
    console.log(this.routePath);
  }

}
