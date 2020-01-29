import { SakuraDataSourceManager } from 'src/app/sakura.datasource.manager';
import { AgarthaDataSourceManager } from './data.source.manager';

export class AgarthaAppContainer {

    private static dataSourceManager: SakuraDataSourceManager;
    private static initDataSourceManager: AgarthaDataSourceManager;

    public static getAppStorage(): SakuraDataSourceManager {
        if(!this.dataSourceManager) {
            this.dataSourceManager = new SakuraDataSourceManager();
        }
        return this.dataSourceManager;
    }

    public static getInitDataStorage(): AgarthaDataSourceManager {
        if(!this.initDataSourceManager) {
            this.initDataSourceManager = new AgarthaDataSourceManager();
        }
        return this.initDataSourceManager;
    }
}