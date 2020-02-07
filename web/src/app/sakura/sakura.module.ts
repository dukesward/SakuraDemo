import { KingdomCharacterBaseCard } from './agartha/kingdom/content/cards/character-base-card/character-base-card.component';
import { KingdomCharacterCardDirective } from './agartha/kingdom/templates/character-card.directive';
import { KingdomCharacterCard } from './agartha/kingdom/content/cards/character-card/character-card.component';
import { KingdomEquipmentComponent } from './agartha/kingdom/widgets/equipment/kingdom.equipment';
import { KingdomPublicOfferDirective } from './agartha/kingdom/templates/pub-offer.directive';
import { KingdomPublicOffer } from './agartha/kingdom/content/events/pub-offer/pub-offer.component';
import { KingdomQuestReward } from './agartha/kingdom/content/quest/quest-reward/quest-reward.component';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { PortalComponent } from './portal/portal.component';
import { SakuraRoutingModule } from './sakura-routing.module';
import { AuthBoxComponent } from '../modules/forms/auth-box/auth-box.component';
import { FormBasicComponent } from '../modules/forms/form-basic/form-basic.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ImgLoaderComponent } from '../modules/common/img-loader/img-loader.component';
import { AssetLoaderComponent } from '../modules/common/asset-loader/asset-loader.component';
import { ApiProxyComponent } from '../modules/common/api-proxy/api-proxy.component';
import { AgarthaComponent } from './agartha/agartha.component';
import { BohemianMenuComponent } from '../modules/custom/bohemian/menu/menu.component';
import { BohemianComponent } from '../modules/custom/bohemian/bohemian.component';
import { StageComponent } from '../modules/custom/bohemian/stage/stage.component';
import { WhiteBoardComponent } from '../modules/common/white-board/white-board.component';
import { BohemianDirective } from '../modules/custom/bohemian/directives/bohemian.directive';
import { AgarthaModuleComponent } from './agartha/components/agartha-module/agartha-module.component';
import { KingdomComponent } from './agartha/kingdom/kingdom.component';
import { EmptyItemComponent } from './agartha/kingdom/content/empty-item/empty-item.component';
import { MainframeComponent } from './agartha/kingdom/mainframe/mainframe/mainframe.component';
import { KingdomEventWrapperComponent } from './agartha/kingdom/content/event-wrapper/event-wrapper.component';
import { KingdomPublicAnnouncement } from './agartha/kingdom/content/events/pub-anoun/pub-ann.component';
import { KingdomPublicAnnounceDirective } from './agartha/kingdom/templates/pub-ann.directive';
import { ForgeComponent } from './agartha/kingdom/mainframe/forge/forge.component';

@NgModule({
  declarations: [
    // common
    ApiProxyComponent,
    AssetLoaderComponent,
    ImgLoaderComponent,
    WhiteBoardComponent,
    // forms
    FormBasicComponent,
    AuthBoxComponent,
    // custom
    BohemianMenuComponent,
    BohemianComponent,
    StageComponent,
    // custom components
    AgarthaModuleComponent,
    // directives
    BohemianDirective,
    KingdomPublicAnnounceDirective,
    KingdomPublicOfferDirective,
    KingdomCharacterCardDirective,
    // sakura core
    PortalComponent,
    // agartha
    AgarthaComponent,
    EmptyItemComponent,
    // kingdom
    KingdomComponent,
    MainframeComponent,
    ForgeComponent,
    KingdomEventWrapperComponent,
    KingdomPublicAnnouncement,
    KingdomPublicOffer,
    KingdomQuestReward,
    KingdomEquipmentComponent,
    KingdomCharacterCard,
    KingdomCharacterBaseCard
  ],
  entryComponents: [
    WhiteBoardComponent,
    AgarthaModuleComponent,
    KingdomEventWrapperComponent,
    KingdomPublicAnnouncement,
    KingdomPublicOffer,
    KingdomCharacterCard
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    SakuraRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
})
export class SakuraModule { }
