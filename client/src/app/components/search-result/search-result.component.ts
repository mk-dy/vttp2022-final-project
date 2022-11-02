import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Product } from '../../models';
import { SearchService } from '../../services/search.service';

@Component({
  selector: 'app-search-result',
  templateUrl: './search-result.component.html',
  styleUrls: ['./search-result.component.css']
})
export class SearchResultComponent implements OnInit {

  productList!: Product[]
  sub$!: Subscription

  constructor(private searchSvc: SearchService) { }
 
  ngOnInit(): void {
    // this.searchSvc.findProduct() //
    this.sub$ = this.searchSvc.onSearchResult.subscribe( data => {
      this.productList = data
    })
  }
  // display all results here 
}
