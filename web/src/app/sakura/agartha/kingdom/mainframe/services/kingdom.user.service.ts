import { KingdomActionHandler } from './kingdom.action.handler';
import { Injectable } from "@angular/core";
import { KingdomContentService } from '../../services/kingdom.content.service';
import { SakuraEntityValidationProxy } from 'src/app/sakura.entity.validator';
import { SakuraValidatorBasic } from 'src/app/models/utility/validator.basic';
import { utils } from '../../../../../common-utils';

@Injectable({
    providedIn: "root"
})
export class KingdomUserService extends KingdomContentService {

    constructor(protected actionHandler: KingdomActionHandler) {
        super(actionHandler);
        this._hasHeader = true;
    }

    getHeaders() {
        return {
            "userProfileId": this.initDataStorage.getOne(this.hashcode, "userProfileId")
        };
    }

    buildValidator(target: any): SakuraValidatorBasic {
        return SakuraEntityValidationProxy.hasKey(target, "name");
    }

    getUserName(): string {
        var userProfile = this.entityStorage.get('kingdomUserProfile');
        return userProfile ? utils.capitalize(userProfile['name']) : null;
    }

    getUserProfileId(): string {
        return this.entityStorage.get('kingdomUserProfile')['userId'];
    }

    getUserLocationName(): string {
        return this.entityStorage.get('kingdomLocation')['enName'];
    }
}