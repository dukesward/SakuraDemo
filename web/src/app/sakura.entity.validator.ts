import { SakuraValidatorBasic } from './models/utility/validator.basic';
import { SakuraSuccessValidator } from './models/utility/success.validator';
import { SakuraFailureValidator } from './models/utility/failure.validator';
import { SakuraValidatorImpl } from './models/utility/validator.impl';

export class SakuraEntityValidationProxy {

    public static validate(validator: SakuraValidatorBasic): boolean {
        return validator.test();
    }

    public static success(): SakuraValidatorBasic {
        return new SakuraSuccessValidator();
    }

    public static failure(): SakuraValidatorBasic {
        return new SakuraFailureValidator();
    }

    public static equals<T>(target: any, prop: T): SakuraValidatorBasic {
        return new SakuraValidatorImpl(() => {
            return target === prop;
        });
    }

    public static hasKey(target: any, key: string): SakuraValidatorBasic {
        return new SakuraValidatorImpl(() => {
            return target[key] != null;
        });
    }

    public static isArray(target: any): SakuraValidatorBasic {
        return new SakuraValidatorImpl(() => Object.prototype.toString.call(target) === "[object Array]");
    }
}