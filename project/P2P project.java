整体
It is about a P2P file sharing system, 
    which consists of a group of Peers and a Register-Server that can handle Concurrent requests.

Also designed the Communication-Protocols according to the standered HTTP protocol.

Implemented Distributed-Index at the server to store the metadata for each peer 
    to accelerate the transmission speed and eliminate redundant transmissions.



特点
Rather than using the centralized server for downloading files, 
the P2P-DI system in which peers who wish to download a file that they do not have in their hard drive, 
may download it from another Active-Peer who does.

It just like a simple version of BitTorrent

Each peer maintains an RFC File-Index with the infomation about the RFC files it has locally.
the peer will run as an RFC-Server that allows other peers contact to download, 
and it also runs as an RFC-Client that itself needs to download.


流程
So there are Two components of the system, a Concurrent RS server and many Peers.
When a peer wants to join the P2P system, it 
    1. opens a connection the the RS server, and it will receive a Cookie if it was the first time connection.
When a peer wants to download, 
    1. send request to RS to query the Active peer list.
    2. then pick up a random peer B from the list, and send request to get its RFC-Index
    3. after got response from B, 
        3.1 merges it with its own RFC-Index,
        3.2 searches its new RFC index (after the merge) to find any active peer that has the RFC it is looking for
        
        If the RFC-Index indicates that some active peer C has the RFC, 
        A opens a new connection to the RFC server of C to download the RFC 

 This process continues until either A successfully downloads the RFC or the list of active peers is exhausted.
















































