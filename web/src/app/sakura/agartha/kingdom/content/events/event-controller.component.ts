import { SchemaBasic } from './../../../../../models/schema.basic';
import { WhiteBoardComponent } from './../../../../../modules/common/white-board/white-board.component';
import { KingdomEventBasic } from '../../../entities/kingdom/event.global.entity';
import { SakuraSchemaManager } from 'src/app/sakura.schema.manager';
import { Component } from '@angular/core';

@Component({})
export class KingdomEventController extends WhiteBoardComponent {
	
	protected entity: KingdomEventBasic;

	ngOnInit(): void {}

	constructor(protected schemaManager: SakuraSchemaManager) {
    	super(schemaManager);
    	this.schemaCache.put("kingdom-event-exception", this.schemaManager.loadSchema("kingdom-event", "exception"));
	}

	getMainCharacter(): string {
		var proxy = this.entity.getService("kingdomCharacter");
		var mainCharacter = proxy.getMainCharacter();
		return mainCharacter ? ('characters/' + mainCharacter.uuid) : null;
	}

	getContentSchema(prop: string): string {
    var schema = this.entity.schema;
    return schema.get(prop).translate({
      title: this.entity.title
    });
	}

	getEventSchema(property: string, template: any = null) {
		return this.getSchemaById(property, 'kingdom-event', template);
	}

	getSchemaById(property: string, eventSchemaId: string, template: any = null) {
    var keyValPair: string[] = property.split(':');
    var schema: SchemaBasic = null;
    if(keyValPair.length == 2) {
      schema = this.doGetSchema(eventSchemaId, keyValPair[0], keyValPair[1]);
    }
    if(!schema) {
      schema = this.doGetSchema(eventSchemaId, "exception", "schema-undefined");
    }
    return template ? schema.translate(template) : schema.getContent();
	}
}