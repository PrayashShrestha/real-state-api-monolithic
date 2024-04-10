package miu.ea.realestateapimonolithic.controller;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.common.Constant;
import miu.ea.realestateapimonolithic.dto.AgentReviewDto;
import miu.ea.realestateapimonolithic.dto.PropertyDto;
import miu.ea.realestateapimonolithic.dto.SearchConditionDto;
import miu.ea.realestateapimonolithic.model.Buyer;
import miu.ea.realestateapimonolithic.model.BuyerPreference;
import miu.ea.realestateapimonolithic.service.AgentReviewService;
import miu.ea.realestateapimonolithic.service.BuyerService;
import miu.ea.realestateapimonolithic.service.SearchConditionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constant.BUYER_URL_PREFIX)
public class BuyerController {
    private final BuyerService buyerService;
    private final AgentReviewService agentReviewService;
    private final SearchConditionService searchConditionService;

    @GetMapping("/{id}")
    public ResponseEntity<Buyer> getBuyerById(@PathVariable Long id) {
        return new ResponseEntity<>(buyerService.getBuyerById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}/update-preferences")
    public ResponseEntity<String> updateBuyerPreferences(@PathVariable Long id, @RequestBody BuyerPreference buyerPreferences) {
        buyerService.updateBuyerPreferences(id, buyerPreferences);
        return new ResponseEntity<>("Updated Successfully.", HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addAgentReview(@RequestBody AgentReviewDto agentReviewDto) {
        agentReviewService.saveAgentReview(agentReviewDto);
        return new ResponseEntity<>("Review added successfully", HttpStatus.OK);
    }

    @PostMapping("{buyerId}/favorites")
    public ResponseEntity<String> addFavoriteProperty(@PathVariable Long buyerId, @RequestBody PropertyDto propertyDto){
        buyerService.addFavouriteProperty(buyerId, propertyDto);
        return new ResponseEntity<>("Property added to favourites", HttpStatus.OK);
    }

    @PostMapping("{buyerId}/removeFavorites")
    public ResponseEntity<String> removeFavoriteProperty(@PathVariable Long buyerId, @RequestBody PropertyDto propertyDto){
        buyerService.removeFavouriteProperty(buyerId, propertyDto);
        return new ResponseEntity<>("Property removed from favourites", HttpStatus.OK);
    }

    @GetMapping("{buyerId}/favorites")
    public ResponseEntity<List<PropertyDto>> getFavoriteProperties(@PathVariable Long buyerId){
        List<PropertyDto> favorites = buyerService.viewFavouriteProperties(buyerId);
        return new ResponseEntity<>(favorites, HttpStatus.OK);
    }

    @PostMapping("{buyerId}/conditions")
    public ResponseEntity<String> saveSearchCondition(@PathVariable Long buyerId, @RequestBody SearchConditionDto searchConditionDto){
        searchConditionService.saveSearchCondition(buyerId, searchConditionDto);
        return new ResponseEntity<>("Search Condition Saved", HttpStatus.OK);
    }

    @GetMapping("{buyerId}/conditions")
    public ResponseEntity<List<SearchConditionDto>> viewSavedSearchConditions(@PathVariable Long buyerId){
        List<SearchConditionDto> searchConditions = searchConditionService.getAllSearchCondition(buyerId);
        return new ResponseEntity<>(searchConditions, HttpStatus.OK);
    }

    @PutMapping("{buyerId}/conditions/{searchConditionId}")
    public ResponseEntity<String> editSavedSearchConditions(@PathVariable Long buyerId, @PathVariable Long searchConditionId, @RequestBody SearchConditionDto searchConditionDto){
        searchConditionService.editSearchCondition(buyerId, searchConditionId, searchConditionDto);
        return new ResponseEntity<>("Search Condition Updated", HttpStatus.OK);
    }

    @DeleteMapping("{buyerId}/conditions/{searchConditionId}")
    public ResponseEntity<String> deleteSavedSearchCondition(@PathVariable Long buyerId,@PathVariable Long searchConditionId){
        searchConditionService.deleteSearchCondition(buyerId, searchConditionId);
        return new ResponseEntity<>("Deleted Search Condition", HttpStatus.OK);
    }
}
