package markd315.io.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * QueryResponse
 */
@javax.annotation.Generated(value = "io.io.codegen.languages.SpringCodegen", date = "2018-08-04T19:33:03.042-04:00")

public class QueryResponse   {
  @JsonProperty("notBlocked")
  private Boolean notBlocked = null;

  public QueryResponse notBlocked(Boolean notBlocked) {
    this.notBlocked = notBlocked;
    return this;
  }

   /**
   * Get notBlocked
   * @return notBlocked
  **/
  @ApiModelProperty(value = "")


  public Boolean notBlocked() {
    return notBlocked;
  }

  public void setNotBlocked(Boolean notBlocked) {
    this.notBlocked = notBlocked;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QueryResponse queryResponse = (QueryResponse) o;
    return Objects.equals(this.notBlocked, queryResponse.notBlocked);
  }

  @Override
  public int hashCode() {
    return Objects.hash(notBlocked);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QueryResponse {\n");
    
    sb.append("    notBlocked: ").append(toIndentedString(notBlocked)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

