import { SakuraValidatorBasic } from './validator.basic';

export class SakuraSuccessValidator implements SakuraValidatorBasic {

    private _validator: () => boolean;

    constructor() {
        this._validator = () => { return true; };
    }

    test(): boolean {
        return this._validator.call(this);
    }

    and(other: SakuraValidatorBasic): SakuraValidatorBasic {
        return other.test() ? this : other;
    }

    or(other: SakuraValidatorBasic): SakuraValidatorBasic {
        return this;
    }
}