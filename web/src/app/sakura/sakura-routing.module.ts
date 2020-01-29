import { Routes, RouterModule } from "@angular/router";
import { NgModule } from '@angular/core';
import { PortalComponent } from './portal/portal.component';
import { AgarthaComponent } from './agartha/agartha.component';
import { KingdomComponent } from './agartha/kingdom/kingdom.component';
import { MainframeComponent } from './agartha/kingdom/mainframe/mainframe/mainframe.component';

const routes: Routes = [
    { 
        path: 'sakura',
        children: [
            {
                path: 'portal',
                component: PortalComponent
            },
            {
                path: 'signup',
                component: PortalComponent
            },
            {
                path: '',
                pathMatch: 'full',
                redirectTo: '/sakura/portal'
            },
            {
                path: 'admin',
                children: [
                    {
                        path: '',
                        component: AgarthaComponent,
                    },
                    {
                        path: 'kingdom',
                        component: KingdomComponent
                    }
                ]
            },
            {
                path: 'kingdom',
                children: [
                    {
                        path: 'home',
                        component: MainframeComponent
                    }
                ]
            }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})

export class SakuraRoutingModule {}