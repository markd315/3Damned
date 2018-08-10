package markd315.io.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.*;

/**
 * Hash
 */
@javax.annotation.Generated(value = "io.io.codegen.languages.SpringCodegen", date = "2018-08-04T19:33:03.042-04:00")

public class Hash   {
  @JsonProperty("digest")
  private String digest = null;

  public Hash digest(String digest) {
    this.digest = digest;
    return this;
  }

   /**
   * Get digest
   * @return digest
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getDigest() {
    return digest;
  }

  public void setDigest(String digest) {
    this.digest = digest;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Hash hash = (Hash) o;
    return Objects.equals(this.digest, hash.digest);
  }

  @Override
  public int hashCode() {
    return Objects.hash(digest);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Hash {\n");
    
    sb.append("    digest: ").append(toIndentedString(digest)).append("\n");
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

