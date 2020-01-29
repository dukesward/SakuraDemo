import { Injectable } from "@angular/core";
import schemaConfigs from "../assets/schema/sakura/schema.json";
import definitions from "../assets/schema/sakura/definition.json";
import { SchemaBasic } from './models/schema.basic.js';
import { Hashmap } from './models/data-structure/hashmap.model.js';

@Injectable({
    providedIn: "root"
})
export class SakuraSchemaManager {

    public loadSchema(moduleId: string, componentId: string) {
        if(schemaConfigs[moduleId]) {
            var schemaConfig = schemaConfigs[moduleId];
            if(schemaConfig && schemaConfig[componentId]) {
                var component = schemaConfig[componentId];
                if(component) {
                    return this.buildSchemaObject(component);
                }else {
                    return this.loadSchema(this.getSchemaParent(moduleId), componentId);
                }
            }else {
                return this.loadSchema(this.getSchemaParent(moduleId), componentId);
            }
        }
        return null;
    }

    public getSchemaParent(moduleId: string): string {
        var definition = this.loadDefinition(moduleId, "schema");
        if(definition && definition.parent) {
            return definition.parent;
        }
        return null;
    }

    public loadDefinition(componentId: string, definitionId: string = null) {
        if(definitions[componentId]) {
            if(definitionId) {
                var component = definitions[componentId];
                if(component[definitionId]) {
                    return component[definitionId];
                }
            }else {
                return definitions[componentId];
            }
        }
        return null;
    }

    buildSchemaObject(components: object): Hashmap<SchemaBasic> {
        var schemaCollection = new Hashmap<SchemaBasic>();
        for(var key in components) {
            var contentSchema = components[key];
            schemaCollection.put(key, new SchemaBasic(contentSchema));
        }
        return schemaCollection;
    }
}