package adlere.ylaurelut.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Global object mapping to the JSON structure of the output file
 * 
 * @author ylaurelu
 *
 */
public class CustomerSummariesOutput {
	private List<CustomerSummary> customerSummaries;
	
	public CustomerSummariesOutput() {
		this.customerSummaries = new ArrayList<CustomerSummary>();
	}
	
	public void addCustomerSummary(CustomerSummary customerSummary) {
		this.customerSummaries.add(customerSummary);
	}

	public List<CustomerSummary> getCustomerSummaries() {
		return customerSummaries;
	}
}
