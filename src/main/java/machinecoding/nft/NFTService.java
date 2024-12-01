package machinecoding.nft;

import java.util.HashMap;
import java.util.Map;

public class NFTService {
    private Map<String, User> users; // Map of UserID to User objects

    public NFTService() {
        this.users = new HashMap<>();
    }

    public NFT mintNFT(String nftId, String userId, String metadata) {
        NFT newNFT = new NFT(nftId, userId, metadata);
        User user = users.getOrDefault(userId, new User(userId));
        user.addNFT(newNFT);
        users.put(userId, user);
        return newNFT;
    }

    public boolean transferNFT(String nftId, String fromUserId, String toUserId) {
        User fromUser = users.get(fromUserId);
        User toUser = users.getOrDefault(toUserId, new User(toUserId));

        NFT nftToTransfer = null;
        for (NFT nft : fromUser.getOwnedNFTs()) {
            if (nft.getId().equals(nftId)) {
                nftToTransfer = nft;
                break;
            }
        }

        if (nftToTransfer == null) {
            return false; // NFT not found
        }

        fromUser.removeNFT(nftToTransfer);
        nftToTransfer.setOwner(toUserId);
        toUser.addNFT(nftToTransfer);
        users.put(toUserId, toUser);
        return true; // Transfer successful
    }
}
