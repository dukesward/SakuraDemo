import { EntityBasic } from 'src/app/models/entity.basic';

export class KingdomQuestEntity implements EntityBasic {

    private _questId: string;
    private _requireLevel: number;
    private _rewardNumber: number;
    private _reward1: string;
    private _reward2: string;
    private _reward3: string;
    private _reward4: string;

    public constructor(questId: string) {
        this._questId = questId;
    }

    get questId(): string {
        return this._questId;
    }

    set requireLevel(requireLevel: number) {
        this._requireLevel = requireLevel;
    }

    get requireLevel(): number {
        return this._requireLevel;
    }

    set rewardNumber(rewardNumber: number) {
        this._rewardNumber = rewardNumber;
    }

    get rewardNumber(): number {
        return this._rewardNumber;
    }

    set reward1(reward1: string) {
        this._reward1 = reward1;
    }

    get reward1(): string {
        return this._reward1;
    }

    set reward2(reward2: string) {
        this._reward2 = reward2;
    }

    get reward2(): string {
        return this._reward2;
    }

    set reward3(reward3: string) {
        this._reward3 = reward3;
    }

    get reward3(): string {
        return this._reward3;
    }

    set reward4(reward4: string) {
        this._reward4 = reward4;
    }

    get reward4(): string {
        return this._reward4;
    }
}