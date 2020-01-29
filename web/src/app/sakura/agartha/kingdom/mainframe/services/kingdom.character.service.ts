import { KingdomActionHandler } from './kingdom.action.handler';
import { KingdomEventBasic } from 'src/app/sakura/agartha/entities/kingdom/event.global.entity';
import { EntityBasic } from 'src/app/models/entity.basic';
import { KingdomContentService } from '../../services/kingdom.content.service';
import { Injectable } from '@angular/core';
import { SakuraEntityValidationProxy } from 'src/app/sakura.entity.validator';
import { SakuraValidatorBasic } from 'src/app/models/utility/validator.basic';

@Injectable({
    providedIn: "root"
})
export class KingdomCharacterService extends KingdomContentService {

    constructor(protected actionHandler: KingdomActionHandler) {
        super(actionHandler);
    }

    handle(): EntityBasic {
        var items = [];
        var characters = this.entityStorage.get("kingdomCharacter");
        for(var i=0; i<characters.length; i++) {
            items.push(this.buildEventEntity(characters[i]));
        }
        return items;
    }

    buildEventEntity(item: any) {
        var event = new KingdomEventBasic(item['uuid'], this);
        event['type'] = "character-card";
        return event;
    }

    buildValidator(target: any): SakuraValidatorBasic {
        return SakuraEntityValidationProxy.success();
    }

    getNumberOfCharacters(): number {
        var characters = this.entityStorage.get("kingdomCharacter");
        if(characters) {
            return characters['length'];
        }
    }

    getCharacterNames(): string[] {
        var characters = this.entityStorage.get("kingdomCharacter");
        var names: string[] = [];
        for(var i=0; i<characters.length; i++) {
            names.push(characters[i].name);
        }
        return names;
    }

    getCharacterNameAliases(): string[] {
        var characters = this.entityStorage.get("kingdomCharacter");
        var names: string[] = [];
        for(var i=0; i<characters.length; i++) {
            names.push(characters[i].nameEn);
        }
        return names;
    }

    getMainCharacter(): any {
        var characters = this.getCharacterCollection();
        for(var i=0; i<characters.length; i++) {
            if(characters[i].status === 'M') {
                return characters[i];
            }
        }
        return null;
    }

    getCharacterCollection(): any[] {
        return this.entityStorage.get("kingdomCharacter");
    }
}