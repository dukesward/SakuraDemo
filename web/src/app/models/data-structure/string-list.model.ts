import { List } from './list.model';
import { TypedList } from './typed-list.model';

export class StringList extends TypedList<string> {
    list: string[];

    constructor(list: string[] = null) {
        super(list);
    }

    isEmpty(element: string) {
        return element === '';
    }  
}
