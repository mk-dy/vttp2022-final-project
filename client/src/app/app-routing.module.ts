import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

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
import { OrdersComponent } from './components/orders/orders.component';
import { FooterComponent } from './components/footer/footer.component';
import { CheckoutComponent } from './components/checkout/checkout.component';
import { PaymentService } from './services/payment.service';
import { PaymentCancelComponent } from './components/payment-cancel/payment-cancel.component';
import { PaymentSuccessComponent } from './components/payment-success/payment-success.component';
import { LandingComponent } from './components/landing/landing.component';
import { CartComponent } from './components/cart/cart.component';
import { AuthenticationGuard } from './auth.guard';
import { OrderDetailsComponent } from './components/orders/order-details.component';

const appRoutes: Routes = [
    { path: '', component: LandingComponent },
    { path: 'login', component: LoginComponent },
    { path: 'signup', component: CreateUserComponent},
    { path: 'shop', component: MainComponent},
    { path: 'search', component: SearchResultComponent },
    { path: 'cart', component: CartComponent, canActivate:[AuthenticationGuard]},
    { path: 'orders', component: OrdersComponent},
    { path: 'orders/:id', component: OrderDetailsComponent}, // to add parameterized routes to show different orders
    { path: 'product/chalk-bag', component: BagComponent},
    { path: 'product/chalk-bucket', component: BucketComponent},
    { path: 'favourites', component: FavouritesComponent, canActivate:[AuthenticationGuard]},
    { path: 'checkout', component: CheckoutComponent, canActivate:[AuthenticationGuard]},
    { path: '**', redirectTo: '/', pathMatch : 'full'}
  ]

@NgModule({
  imports: [RouterModule.forRoot(appRoutes,{useHash: true})],
  exports: [RouterModule],
})
export class AppRoutingModule {}