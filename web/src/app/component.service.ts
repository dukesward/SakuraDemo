import { Injectable } from '@angular/core';
import Registered from '../assets/configs/registered.components';
import { WhiteBoardComponent } from './modules/common/white-board/white-board.component';

@Injectable({
    providedIn: 'root'
})
export class ComponentService {

    constructor() {}

    getComponent(key: string) {
        return Registered[key] ? Registered[key]["type"] : WhiteBoardComponent;
    }
}