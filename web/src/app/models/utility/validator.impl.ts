import { SakuraValidatorBasic } from './validator.basic';
import { SakuraSuccessValidator } from './success.validator';
import { SakuraFailureValidator } from './failure.validator';

export class SakuraValidatorImpl implements SakuraValidatorBasic {

    private _validator: () => boolean;

    constructor(validator: () => boolean) {
        this._validator = validator;
    }

    test(): boolean {
        return this._validator.call(this);
    }

    and(other: SakuraValidatorBasic): SakuraValidatorBasic {
        return this.test() && other.test() ? new SakuraSuccessValidator() : new SakuraFailureValidator();
    }

    or(other: SakuraValidatorBasic): SakuraValidatorBasic {
        return this.test() || other.test() ? new SakuraSuccessValidator() : new SakuraFailureValidator();
    }
}