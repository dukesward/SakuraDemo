import { Hashmap } from 'src/app/models/data-structure/hashmap.model';
import { Component, OnInit } from '@angular/core';
import { EntityBasic } from 'src/app/models/entity.basic';
import { SakuraSchemaManager } from 'src/app/sakura.schema.manager';
import { environment } from '../../../../environments/environment';
import { SchemaBasic } from 'src/app/models/schema.basic';

@Component({
  selector: 'white-board',
  templateUrl: './white-board.component.html',
  styleUrls: ['./white-board.component.scss']
})
export class WhiteBoardComponent implements OnInit {

  protected entity: EntityBasic;
  protected moduleId: string;
  protected environment;
  protected schemaCache: Hashmap<Hashmap<SchemaBasic>>;

  constructor(protected schemaManager: SakuraSchemaManager) {
    this.environment = environment;
    this.moduleId = 'kingdom-white-board';
    this.schemaCache = new Hashmap<Hashmap<SchemaBasic>>();
  }

  ngOnInit() {
  }

  setEntity(entity: EntityBasic) {
    this.entity = entity;
  }

  get(property: string) {
    if(this.entity[property]) {
      return this.entity[property];
    }
    return null;
  }

  getElementClass(element: string) {
    var theme: string = environment.profuctionContent.theme;
    var schema: SchemaBasic = this.schemaManager.loadSchema(this.moduleId, element).get("class-" + theme);
    return schema.getContent();
  }

  doGetSchema(schemaId: string, componentId: string, key: string) {
    var cacheId = schemaId + '-' + componentId;
    if(!this.schemaCache.get(cacheId)) {
      var schema = this.schemaManager.loadSchema(schemaId, componentId);
      if(schema) {
        this.schemaCache.put(cacheId, schema);
      }else {
        console.log('Schema is not defined');
      }
    }
    return this.schemaCache.get(cacheId).get(key);
  }

}
