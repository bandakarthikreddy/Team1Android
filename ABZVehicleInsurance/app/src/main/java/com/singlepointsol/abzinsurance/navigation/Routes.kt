package com.singlepointsol.abzinsurance.navigation

sealed class Routes(val route: String) {
    object Splash : Routes("splash")
    object Blankpage : Routes("blank_page")
    object MainPage : Routes("main_page")
    object AgentPage : Routes("agent_page")
    object ClaimPage : Routes("claim_page")
    object CustomerPage : Routes("customer_page")
    object PolicyAddOnPage : Routes("policy_addon_page")
    object PolicyPage : Routes("policy_page")
    object ProposalPage : Routes("proposal_page")
    object VehiclePage : Routes("vehicle_page")
    object ProductPage : Routes("product_page")
    object ProductAddOnPage : Routes("product_addon_page")
    object ProfilePage : Routes("profile_page")
    object ContactUsPage : Routes("contact_us_page")
    object CustomerQueryPage : Routes("customer_query_page")
    object QueryResponsePage : Routes("query_response_page")
}
