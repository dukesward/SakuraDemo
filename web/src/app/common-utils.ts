export class utils {

    public static capitalize(str: string): string {
        return str[0].toLocaleUpperCase() + str.substr(1);
    }

    public static inArray(arr: any[], target: any) {
        for(var i=0; i<arr.length; i++) {
            if(arr[i] != null && arr[i] === target) {
                return true;
            }
        }
        return false;
    }

    public static isFunction(target: any): boolean {
        return target && {}.toString.call(target) === '[object Function]';
    }

    public static objectToArray(src: any) {
        var keys = Object.keys(src);
        var output = [];
        for(var i=0; i<keys.length; i++) {
            output.push(src[keys[i]]);
        }
        return output;
    }

    public static reformat(str: string, delimiter: string): string {
        var tokens: string[] = str.split(delimiter);
        return tokens.map((s) => this.capitalize(s)).join('');
    }

    public static reformatCamel(str: string, delimiter: string): string {
        var reformatted = this.reformat(str, delimiter);
        return reformatted[0].toLowerCase() + reformatted.substr(1);
    }
}