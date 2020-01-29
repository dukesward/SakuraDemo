export interface SakuraValidatorBasic {

    test(): boolean;

    and(other: SakuraValidatorBasic): SakuraValidatorBasic;

    or(other: SakuraValidatorBasic): SakuraValidatorBasic;
}