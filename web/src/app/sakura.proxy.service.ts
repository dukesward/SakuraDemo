import { utils } from './common-utils';

export class SakuraProxyService {
    
    protected handler = {
        apply: (target: any, objectKey: string) => {
            if(objectKey) {
                console.log(target);
                if(utils.isFunction(target[objectKey])) {
                    return target[objectKey].call(target);
                }else if(utils.isFunction(this[objectKey])) {
                    return this[objectKey].call(this, target);
                }
            }
        }
    }

    build(target: any) {
        return new Proxy(target, this.handler);
    }
    
}