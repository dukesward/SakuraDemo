import { Injectable } from '@angular/core';
import { ModuleEntity } from '../entities/module.entity';
import { AgarthaServiceBasic } from './service.basic';

@Injectable({
  providedIn: 'root'
})
export class ModuleService extends AgarthaServiceBasic {

  constructor() {
    super();
  }

  handle(data: any): ModuleEntity[] {
    var modules = [];
    console.log(data);
    for(var module in data.modules) {
      var moduleConfigs = data.modules[module];
      if(moduleConfigs) {
        var moduleEntity = new ModuleEntity(moduleConfigs["component"]);
        var config = moduleConfigs["config"];
        moduleEntity.moduleName = config.id;
        moduleEntity.description = config.description;
        moduleEntity.group = config.group;
        moduleEntity.interface = config.interface;
        moduleEntity.structure = config.structure;
        moduleEntity.installed = config.installed;
        modules.push(this.build(moduleEntity));  
      }
    }
    return modules;
  }
}
