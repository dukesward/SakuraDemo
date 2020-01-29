import * as uuid from 'uuid';
import { AgarthaDataSourceManager } from './data.source.manager';
import { AgarthaAppContainer } from './container';
import { SakuraDataSourceManager } from 'src/app/sakura.datasource.manager';
import { SakuraValidatorBasic } from 'src/app/models/utility/validator.basic';
import { SakuraEntityValidationProxy } from 'src/app/sakura.entity.validator';
import { EntityBasic } from 'src/app/models/entity.basic';
import { Hashmap } from 'src/app/models/data-structure/hashmap.model';
import { SakuraProxyService } from 'src/app/sakura.proxy.service';

export class AgarthaServiceBasic extends SakuraProxyService {

    protected hashcode: string;
    protected initDataStorage: AgarthaDataSourceManager;
    protected entityStorage: SakuraDataSourceManager;

    constructor() {
        super();
        this.hashcode = uuid.v4();
        this.initDataStorage = AgarthaAppContainer.getInitDataStorage();
        this.entityStorage = AgarthaAppContainer.getAppStorage();
    }

    getInitData(): Hashmap<any> {
        return this.initDataStorage.get(this.hashcode);
    }

    prepare(key: string, val: any): void {
        this.initDataStorage.put(this.hashcode, key, val);
    }

    store(key: string, target: EntityBasic): void {
        this.entityStorage.put(key, target);
    }

    buildValidator(target: any): SakuraValidatorBasic {
        // to be implemented by classes extending
        return SakuraEntityValidationProxy.success();
    }
}