import { HttpServicePlugin } from '../../plugins/http.service.plugin';
import { AgarthaAppContainer } from './container';
import { SakuraDataSourceManager } from 'src/app/sakura.datasource.manager';

export class AgarthaServicePlugin extends HttpServicePlugin {

    protected appStorage: SakuraDataSourceManager;

    constructor() {
        super();
        this.appStorage = AgarthaAppContainer.getAppStorage();
    }
}