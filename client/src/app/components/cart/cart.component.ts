import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { FinalProduct } from 'src/app/models';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  productList!: FinalProduct[]
  sub$!: Subscription

  constructor(private productSvc: ProductService, private route: Router) { }

  ngOnInit(): void {
    this.sub$ = this.productSvc.onGetCartItems.subscribe(data => {
      this.productList = data
    })
    // this.productSvc.getCart().then(result => {
    //   this.productList = result as FinalProduct[]
    // })
    console.info(">>>> productList: ", this.productList)
    
  }

  onCheckout() {
    this.route.navigate(['/checkout'])
  }

}
