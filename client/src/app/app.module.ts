import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LandingComponent } from './components/landing/landing.component';
import { CartComponent } from './components/cart/cart.component';
import { ProductComponent } from './components/product/product.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatMenuModule } from '@angular/material/menu';
import { MatRadioModule } from '@angular/material/radio';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { MatSelectModule } from '@angular/material/select';
import {MatExpansionModule} from '@angular/material/expansion';
import { FlexLayoutModule } from '@angular/flex-layout';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatListModule} from '@angular/material/list';
import {MatTabsModule} from '@angular/material/tabs';


import { CreateUserComponent } from './components/create-user/create-user.component';
import { MainComponent } from './components/main/main.component';
import { SearchService } from './services/search.service';
import { HttpClientModule } from '@angular/common/http';
import { SearchResultComponent } from './components/search-result/search-result.component';
import { BucketComponent } from './components/bucket/bucket.component';
import { ProductService } from './services/product.service';
import { BagComponent } from './components/bag/bag.component';
import { FavouritesComponent } from './components/favourites/favourites.component';
import { LoginComponent } from './components/login/login.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { CreationStatusComponent } from './components/create-user/creation-status.component';
import { OrdersComponent } from './components/orders/orders.component';
import { FooterComponent } from './components/footer/footer.component';
import { CheckoutComponent } from './components/checkout/checkout.component';
import { PaymentService } from './services/payment.service';
import { PaymentCancelComponent } from './components/payment-cancel/payment-cancel.component';
import { PaymentSuccessComponent } from './components/payment-success/payment-success.component';
import { CheckoutService } from './services/checkout.service';
import { AuthService } from './services/auth.service';
import { TokenStorageService } from './services/token-storage.service';
import { authInterceptorProviders } from './auth.interceptor';
import { CartService } from './services/cart.service';





// to change product/chalkbucket into product/{productId}
// or create another path e.g. favourites/{userId}

@NgModule({
  declarations: [
    AppComponent,
    LandingComponent,
    CartComponent,
    ProductComponent,
    CreateUserComponent,
    MainComponent,
    SearchResultComponent,
    BucketComponent,
    BagComponent,
    FavouritesComponent,
    LoginComponent,
    NavbarComponent,
    CreationStatusComponent,
    OrdersComponent,
    FooterComponent,
    CheckoutComponent,
    PaymentCancelComponent,
    PaymentSuccessComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    // RouterModule.forRoot(appRoutes, {useHash: true}),
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatMenuModule,
    MatRadioModule,
    MatFormFieldModule,
    MatInputModule,
    HttpClientModule,
    MatCardModule,
    MatSelectModule,
    FlexLayoutModule,
    MatExpansionModule,
    MatGridListModule,
    MatExpansionModule,
    MatListModule,
    MatTabsModule
  ],
  providers: [
    SearchService, 
    ProductService, 
    PaymentService, 
    CheckoutService, 
    AuthService,
    TokenStorageService,
    authInterceptorProviders, // our interceptor
    CartService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
