import { SakuraValidatorBasic } from './validator.basic';

export class SakuraFailureValidator implements SakuraValidatorBasic {

    private _validator: () => boolean;

    constructor() {
        this._validator = () => { return false; };
    }

    test(): boolean {
        return this._validator.call(this);
    }

    and(other: SakuraValidatorBasic): SakuraValidatorBasic {
        return this;
    }

    or(other: SakuraValidatorBasic): SakuraValidatorBasic {
        return other.test() ? other : this;
    }
}