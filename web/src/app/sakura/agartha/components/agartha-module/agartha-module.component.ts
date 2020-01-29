import { Component, OnInit } from '@angular/core';
import { WhiteBoardComponent } from 'src/app/modules/common/white-board/white-board.component';
import { SakuraSchemaManager } from 'src/app/sakura.schema.manager';

@Component({
  selector: 'app-agartha-module',
  templateUrl: './agartha-module.component.html',
  styleUrls: ['./agartha-module.component.scss']
})
export class AgarthaModuleComponent extends WhiteBoardComponent implements OnInit {

  constructor(protected schemaManager: SakuraSchemaManager) {
    super(schemaManager);
  }

  ngOnInit() {
  }

}
