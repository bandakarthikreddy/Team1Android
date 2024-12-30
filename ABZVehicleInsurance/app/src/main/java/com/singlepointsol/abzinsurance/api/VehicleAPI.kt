import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import com.singlepointsol.abzinsurance.dataclass.VehicleDataClassItem

interface VehicleAPI {

    @GET("Vehicle")
    suspend fun getVehicle(): Response<List<VehicleDataClassItem>>

    @GET("Vehicle/{ownerID}")
    suspend fun getOwners(
        @Path("ownerID") ownerID : String
    ) : Response<VehicleDataClassItem>

    @POST("Vehicle/{regNo}")
    suspend fun addVehicle(
        @Path("regNo") regNo: String,
        @Body vehicle: VehicleDataClassItem
    ): Response<VehicleDataClassItem>

    @GET("Vehicle/{regNo}")
    suspend fun getVehicleByRegNo(
        @Path("regNo") regNo: String
    ): Response<VehicleDataClassItem>

    @DELETE("Vehicle/{regNo}")
    suspend fun deleteVehicle(
        @Path("regNo") regNo: String
    ): Response<Void>

    @PUT("Vehicle/{regNo}")
    suspend fun updateVehicle(
        @Path("regNo") regNo: String,
        @Body vehicle: VehicleDataClassItem
    ): Response<VehicleDataClassItem>
}
