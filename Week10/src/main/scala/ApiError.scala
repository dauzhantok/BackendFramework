import akka.http.scaladsl.model.{StatusCode, StatusCodes}

final case class ApiError private(statusCode: StatusCode, message: String)

object ApiError {
  private def apply(statusCode: StatusCode, message: String): ApiError = new ApiError(statusCode, message)

  val generic: ApiError = new ApiError(StatusCodes.InternalServerError, "Unknown error.")
  val emptyTitleField: ApiError = new ApiError(StatusCodes.BadRequest, message = "Empty title.")
  val emptyDescriptionField: ApiError = new ApiError(StatusCodes.BadRequest, message = "Empty description.")
  val emptyIdField: ApiError = new ApiError(StatusCodes.BadRequest, message = "Empty ID.")
  val duplicateTitle: ApiError = new ApiError(StatusCodes.BadRequest, message = "Duplicate title.")
  def todoNotFound(id: String): ApiError = new ApiError(StatusCodes.NotFound, s"Todo id with $id not found.")
  def todoTitleFound(title: String): ApiError = new ApiError(StatusCodes.BadRequest, s"Todo title $title duplicate.")
}