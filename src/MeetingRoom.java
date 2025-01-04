
public class MeetingRoom implements Borrowable{
	private String name;
    private boolean isAvailable;
    private int floorNumber;
    
    private int capacity;

    public MeetingRoom(String name, boolean isAvailable, int floorNumber, int capacity) {
        this.name = name;
        this.isAvailable = isAvailable;
        this.floorNumber = floorNumber;
        
        this.setCapacity(capacity);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public static MeetingRoom getMeetingRoom(int num) {
    	if(LibrarySystem.meetingRooms.size() < num) {
    		return null;
    	}
    	return LibrarySystem.meetingRooms.get(num-1);
    }

    public static MeetingRoom getMeetingRoomByName(String name) {
      for (MeetingRoom meetingRoom : LibrarySystem.meetingRooms) {
          if (meetingRoom.getName().equals(name)) {
              return meetingRoom;
          }
      }
      return null;
  	
  }

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	@Override
    public void returnItem() {
        this.setAvailable(true);
    }

    @Override
    public void borrowItem() {
        this.setAvailable(false);
    }
}
