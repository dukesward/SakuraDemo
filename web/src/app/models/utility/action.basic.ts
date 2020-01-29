import { EntityBasic } from '../entity.basic';

export class SakuraActionBasic implements EntityBasic {

    protected action: () => void;
    protected actionHandler: any;

    invoke(): void {
        this.action.call(this.actionHandler);
    }
}