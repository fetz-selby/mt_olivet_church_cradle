package com.beta.rsatech.churchcradle.client.app.html.modules.tithes;

import java.util.ArrayList;
import java.util.HashMap;

import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.TitheModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class TitheListPage extends UIObject {

	private ArrayList<String> monthList;
	private ArrayList<TitheModel> modelList;
	private static OfferingListPageUiBinder uiBinder = GWT
			.create(OfferingListPageUiBinder.class);

	interface OfferingListPageUiBinder extends
	UiBinder<Element, TitheListPage> {
	}

	@UiField DivElement divContainer;
	public TitheListPage(ArrayList<TitheModel> modelList) {
		this.modelList = modelList;
		setElement(uiBinder.createAndBindUi(this));
		initMonthList();
		initComponents();
	}

	private void initComponents(){
//		for(String month : monthList){
//			boolean isFound = false;
//			for(TitheModel tmpModel : modelList){
//				if(tmpModel.getMonth().equalsIgnoreCase(month)){
//					setWidget(tmpModel, AppModuleConstants.BG_SUCCESS);
//					isFound = true;
//				}
//			}
//			
//			if(!isFound){
//				setWidget(getEmptyModel(month), AppModuleConstants.BG_DANGER);
//			}
//		}
		
		checkForMultipleMonth(modelList);
	}
	
	private void checkForMultipleMonth(ArrayList<TitheModel> titheModelList){
		HashMap<String, ArrayList<TitheModel>> modelHash = new HashMap<String, ArrayList<TitheModel>>();
		
		for(String month : monthList){
			ArrayList<TitheModel> modelList = new ArrayList<TitheModel>();
			for(TitheModel model : titheModelList){
				if(model.getMonth().equalsIgnoreCase(month)){
					modelList.add(model);
				}
			}
			
			if(modelList.size() > 0){
				modelHash.put(month, modelList);
			}
		}
		
		for(String month : monthList){
			if(modelHash.containsKey(month)){
				ArrayList<TitheModel> modelList = modelHash.get(month);
				double sum = getTitheSum(modelList);
				
				setWidget(modelList, month, sum, AppConstants.BG_SUCCESS);
			}else{
				setWidget(new ArrayList<TitheModel>(), month, 0, AppConstants.BG_DANGER);
			}
		}
		
	}
	
	private double getTitheSum(ArrayList<TitheModel> modelList){
		double sum = 0;
		for(TitheModel model : modelList){
			sum += model.getAmount();
		}
		
		return sum;
	}
	
	private void setWidget(ArrayList<TitheModel> modelList, String month, double amount, String style){
		TitheHTMLComposite composite = new TitheHTMLComposite(modelList, month, amount,style);
		divContainer.appendChild(composite.getElement());
	}

	private void initMonthList(){
		monthList = new ArrayList<String>();
		monthList.add(AppConstants.JANUARY);
		monthList.add(AppConstants.FEBUARY);
		monthList.add(AppConstants.MARCH);
		monthList.add(AppConstants.APRIL);
		monthList.add(AppConstants.MAYW);
		monthList.add(AppConstants.JUNE);
		monthList.add(AppConstants.JULY);
		monthList.add(AppConstants.AUGUST);
		monthList.add(AppConstants.SEPTEMBER);
		monthList.add(AppConstants.OCTOBER);
		monthList.add(AppConstants.NOVEMBER);
		monthList.add(AppConstants.DECEMBER);
	}

	private TitheModel getEmptyModel(String month){
		TitheModel model = new TitheModel();
		model.setAmount(0);
		model.setMonth(month);

		return model;
	}

}
