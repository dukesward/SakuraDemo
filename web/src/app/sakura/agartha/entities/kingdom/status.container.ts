import { EntityBasic } from 'src/app/models/entity.basic';

export class KingdomStatusContainer implements EntityBasic {
    private _type: string;
    private _currentVal: number;
    private _maxVal: number;
    private _bonus: number;

    public constructor(type: string, maxVal: number) {
        this._type = type;
        this._currentVal = maxVal;
        this._maxVal = maxVal;
        this._bonus = 0;
    }

    get type(): string {
        return this._type;
    }

    set type(type: string) {
        this._type = type;
    }

    get currentVal(): number {
        return this._currentVal;
    }

    set currentVal(currentVal: number) {
        this._currentVal = currentVal;
    }

    get maxVal(): number {
        return this._maxVal;
    }

    set maxVal(maxVal: number) {
        this._maxVal = maxVal;
    }

    get bonus(): number {
        return this._bonus;
    }

    set bonus(bonus: number) {
        this._bonus = bonus;
    }
} 