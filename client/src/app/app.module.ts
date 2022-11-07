import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';

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

import { CreateUserComponent } from './components/create-user/create-user.component';
import { UserService } from './services/user.service';
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

const appRoutes: Routes = [
  { path: '', component: LandingComponent },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: CreateUserComponent},
  { path: 'shop', component: MainComponent },
  { path: 'search', component: SearchResultComponent },
  { path: 'cart', component: CartComponent},
  { path: 'orders', component: OrdersComponent}, // to add parameterized routes to show different orders
  { path: 'product', component: ProductComponent},
  { path: 'product/chalk-bag', component: BagComponent},
  { path: 'product/chalk-bucket', component: BucketComponent},
  { path: 'favourites', component: FavouritesComponent},
  { path: '**', redirectTo: '/', pathMatch : 'full'}
]

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
    FooterComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot(appRoutes),
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
    MatGridListModule
  ],
  providers: [UserService, SearchService, ProductService],
  bootstrap: [AppComponent]
})
export class AppModule { }
