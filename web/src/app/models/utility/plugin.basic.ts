import { SakuraEventBasic } from './event.basic';

export interface SakuraPluginBasic {

    matchPluginId(pluginId: string): boolean;

    produce(key: string, event: SakuraEventBasic): any;
}