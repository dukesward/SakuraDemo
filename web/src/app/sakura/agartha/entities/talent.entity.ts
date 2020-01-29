import { EntityBasic } from 'src/app/models/entity.basic';

export class Talent implements EntityBasic {

    private _talentName: string;

    public constructor(talentName: string) {
        this._talentName = talentName;
    }

    get talentName(): string {
        return this._talentName;
    }
}