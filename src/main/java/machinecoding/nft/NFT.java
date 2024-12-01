package machinecoding.nft;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NFT {
    private String id;          // Unique identifier for the NFT
    private String owner;       // User ID of the current owner
    private String metadata;    // Information about the NFT (e.g., name, description)

    public NFT(String id, String owner, String metadata) {
        this.id = id;
        this.owner = owner;
        this.metadata = metadata;
    }

    // Getters and Setters for NFT properties
}