import { KingdomEventController } from './../event-controller.component';
import { Component } from '@angular/core';
import { StringList } from 'src/app/models/data-structure/string-list.model';

@Component({
  templateUrl: './pub-ann.component.html',
  styleUrls: [
    '../../../../../sakura.theme.scss',
    '../../../kingdom.theme.scss',
    '../../event-wrapper/event-wrapper.component.scss',
    './pub-ann.component.scss'
  ]
})
export class KingdomPublicAnnouncement extends KingdomEventController {

  ngOnInit(): void {}

  confirm() {
    this.entity.action('confirm');
  }
  
  getLocationName() {
    var proxy = this.entity.getService("kingdomUserProfile");
    return 'location/' + proxy.getUserLocationName();
  }

  getCharacterAvatar(id: string) {
    return 'characters/' + id;
  }

  hasCharTarget(): boolean {
		return this.entity.definition['characters'] != null;
  }
  
  questView(): boolean {
    return this.entity.quests.length > 0;
  }

	getCharTargets(): StringList {
		var list: StringList = new StringList();
    var charDefinition = this.entity.definition['characters'];
    var proxy = this.entity.getService("kingdomCharacter");
    var characters = proxy.getCharacterCollection();
		if(charDefinition === 'all') {
			list.addAll(characters);
		}
		return list;
  }

  getObjectives(): any[] {
    return this.entity.quests;
  }
}