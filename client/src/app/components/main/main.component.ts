import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Product } from '../../models';
import { ProductService } from '../../services/product.service';
import { SearchService } from '../../services/search.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  productList!: Product[]
  sub$!: Subscription
  searchForm!: FormGroup

  constructor(private fb: FormBuilder, private searchSvc: SearchService, private productSvc: ProductService, private router: Router) { }

  ngOnInit(): void {
    this.productSvc.showProducts()
    this.sub$ = this.productSvc.onShowProducts.subscribe(data => {
      this.productList = data
    })
    this.searchForm = this.createForm()
  }

  createForm(): FormGroup {
    return this.fb.group({
      query: this.fb.control<string>(''),
    })
  }

  performSearch() {
    const data = this.searchForm.value.query
    console.info('>>>> check data: ', data)
    this.searchSvc.findProduct(data)
    this.searchForm.reset()
    this.router.navigate(
      ['/search'],
      { queryParams: { query: data } }
    )
  }


  // in server side, service to pull all the products together with the information intto model,
  // turn model into json format,
  // send the json data over to client service
  // client service uses a GET method
  // what I want is the 
  // PRODUCT IMAGE
  // PRODUCT NAME
  // PRODUCT PRICE

  // SKU 

}
