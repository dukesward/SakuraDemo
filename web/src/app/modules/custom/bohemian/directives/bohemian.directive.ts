import { Directive, Type, ViewContainerRef, ComponentFactoryResolver, OnInit, Input } from '@angular/core';
import { ComponentService } from 'src/app/component.service';
import { WhiteBoardComponent } from 'src/app/modules/common/white-board/white-board.component';
import { EntityBasic } from 'src/app/models/entity.basic';

@Directive({
    selector: 'bohemia'
})
export class BohemianDirective implements OnInit {
    @Input() componentId: string;
    @Input() entity: EntityBasic;

    constructor(
        private componentService: ComponentService,
        public viewContainerRef: ViewContainerRef,
        private componentFactoryResolver: ComponentFactoryResolver
    ) {}

    onShowStart(componentType: Type<WhiteBoardComponent>) {
        this.viewContainerRef.clear();
        const componentFactory = this.componentFactoryResolver.resolveComponentFactory(componentType);
        const componentRef = this.viewContainerRef.createComponent(componentFactory);
        (<WhiteBoardComponent>componentRef.instance).setEntity(this.entity);
    }

    ngOnInit() {
        console.log("directive on start " + this.componentId);
        const componentType = this.componentService.getComponent(this.componentId);
        this.onShowStart(componentType);
    }
}