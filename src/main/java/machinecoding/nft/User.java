package machinecoding.nft;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class User {
    private String userId;                // Unique identifier for the user
    private List<NFT> ownedNFTs;          // List of NFTs owned by the user

    public User(String userId) {
        this.userId = userId;
        this.ownedNFTs = new ArrayList<>();
        int i = 1;
        char c = (char)i;
    }

    // Methods to add or remove NFTs from the user's collection
    public void addNFT(NFT nft) {
        ownedNFTs.add(nft);
    }

    public void removeNFT(NFT nft) {
        ownedNFTs.remove(nft);
    }

    // Getters and Setters for user properties
}
