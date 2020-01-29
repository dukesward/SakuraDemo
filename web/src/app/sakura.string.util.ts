import { utils } from './common-utils';

export class SakuraStringUtil {

    public static format(key: string, source: any): string {
        var functions = {
            "capitalize": s => utils.capitalize(s)
        };
        return functions[key](source) || key;
    }
}