package com.singlepointsol.abzinsurance.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.singlepointsol.abzinsurance.dataclass.ProposalDataClassItem
import com.singlepointsol.abzinsurance.retrofitinstance.ProposalRetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProposalViewModel: ViewModel() {
    private val _proposalData = MutableStateFlow<ProposalDataClassItem?>(null)
    val proposalData: StateFlow<ProposalDataClassItem?> = _proposalData

    fun getProposalByID(proposalNo: String, context: Context) {
        viewModelScope.launch {
            try {
                val response = ProposalRetrofitInstance.proposalAPI.getProposalByID(proposalNo)
                if (response.isSuccessful) {
                    _proposalData.value = response.body()
                    Toast.makeText(context, "Proposal details fetched successfully", Toast.LENGTH_LONG).show()
                    Log.d("ProposalViewModel", "Proposal details fetched successfully")
                } else {
                    _proposalData.value = null
                    Log.e("ProposalViewModel", "Failed to fetch Proposal data: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("ProposalViewModel", "Error fetching Proposal data", e)
                Toast.makeText(context, "Error fetching data: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun addProposal(proposalNo: String, addProposal: ProposalDataClassItem, context: Context) {
        viewModelScope.launch {
            try {
                val response = ProposalRetrofitInstance.proposalAPI.addProposal(proposalNo, addProposal)
                if (response.isSuccessful) {
                    Toast.makeText(context, "Proposal details added successfully", Toast.LENGTH_LONG).show()
                    Log.d("ProposalViewModel", "Proposal details added successfully")
                } else {
                    Log.e("ProposalViewModel", "Failed to add proposal data: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("ProposalViewModel", "Error adding proposal data", e)
                Toast.makeText(context, "Error adding data: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun updateProposal(updateProposal: ProposalDataClassItem, proposalNo: String, context: Context) {
        viewModelScope.launch {
            try {
                val response = ProposalRetrofitInstance.proposalAPI.updateProposal(proposalNo, updateProposal)
                if (response.isSuccessful) {
                    Toast.makeText(context, "Proposal details updated successfully", Toast.LENGTH_LONG).show()
                    Log.d("ProposalViewModel", "Proposal details updated successfully")
                } else {
                    Log.e("ProposalViewModel", "Failed to update proposal data: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("ProposalViewModel", "Error updating proposal data", e)
                Toast.makeText(context, "Error updating data: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun deleteProposal(proposalNo: String, context: Context) {
        viewModelScope.launch {
            try {
                val response = ProposalRetrofitInstance.proposalAPI.deleteProposal(proposalNo)
                if (response.isSuccessful) {
                    Toast.makeText(context, "Proposal details deleted successfully", Toast.LENGTH_LONG).show()
                    Log.d("ProposalViewModel", "Proposal details deleted successfully")
                } else {
                    Log.e("ProposalViewModel", "Failed to delete proposal data: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("ProposalViewModel", "Error deleting proposal data", e)
                Toast.makeText(context, "Error deleting data: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}