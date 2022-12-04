// Doubly Linked List
#include <iostream>
#include <cstring>

using namespace std ;
class Doubly_List
{
      public:
             int data;
             Doubly_List *next , *prev ;

             // Function
             Doubly_List ( )
             {
                 next = prev = 0 ;
             }
};
class Doubly
{
      protected :
              Doubly_List *Head , *Tail, *Current ;
      public:
              Doubly ( )
              {
                     Head = Tail = Current = 0 ;
              }
              void Creation ( ) ;
              void Display ( ) ;
              void Sort123();
};
                // So Due to this we have to do in this formate ( parameter )
void Doubly :: Creation ( )
{
     int check =0 ;

     while ( 1 )
     {

           cout << "\n Enter data : " ;
           cin>>check;
           if ( check ==1 || check==2 || check==3)
          {

           Current = new Doubly_List() ; // Note This step
           Current ->next = 0 ;
           Current ->prev = 0 ;
           Current->data = check ;

           if ( Head == 0 )
           Head = Current ;
           if ( Tail == 0 )
           Tail = Head ;
           else
           {
               Tail ->next = Current ;
               Current ->prev = Tail ;
               Tail = Current ;
           }
        }
        else
        break ;
     }
}


void Doubly :: Display ( )
{
     Current= NULL ;
     if ( Head == NULL )
     {
          cout <<"\n List is Empty\n"  ;
          return ;

     }
     cout << "\n Containt of Link List are : \n" ;
     for ( Current= Head ; Current!=NULL ; Current= Current->next )
     {
         cout <<"\n " << Current-> data ;
     }
     cout << endl ;
}
 void Doubly:: Sort123()
{
    // applying Dutch Flag algo ...insted of swaping  the data we delete that node and add it to proper place
    /*Algo:
    Take two pointers call "Next" and Current
    1> First Search for "1" and make this node as head of Linked List and update head
            ( if already Head data is 1 then skip this step)
    2> now start from second node of List say it being "current"
    3> If current data is "2" do nothing move ahead
    3> if current data is "1" then save next of that node to "Next" and remove this node from the list
    ( rem. do not delete it) and make this node as Head of LL
                and update Head and current = next
                eg . 1  2  1 3  so it become 1 1 2 3
    4> if current data is " 3 " save next of this node in "Next"
    now if Next is null or Next is Tail means List has been Traversed Fully  exit from here
    otherwise
    Make this node as Tail of LL and Update Tail and Current = Next
    proceed it up to end of LL


    */
Doubly_List  *Next ;  // for deleteing a node we save  next node;

Current = Head ;
// Searching For "1" to become head of list
    if ( Current->data!=1)
    {
        while ( Current->next)
        {
            Current = Current->next ;
            if ( Current->data == 1)  // one is found ...so make this node as head node of DLL
            {
                Next = Current->next ;
                Current->prev->next = Next ;
                Next->prev = Current->prev ;
                Current->next = Current->prev = 0 ;
                Current->next = Head ;
                Head->prev = Current ;
                Head = Current ;
                Current= Next ;
                break;
            }
        }
    }

// after making one is head node ...start from seconde node of DLL
Current = Head->next ;

while ( Current->next)
{   if ( Current->data == 2)
        Current = Current->next ;
    else if ( Current->data ==1)  // make this node as head of DLL
    {

                Next = Current->next ;
                if ( !Next ) break ;
                Current->prev->next = Next ;
                Next->prev = Current->prev ;
                Current->next = Current->prev = 0 ;
                Current->next = Head ;
                Head->prev = Current ;
                Head = Current ;
                Current= Next ;

    }
    else if ( Current->data == 3) // make this node as Tail of DLL
    {

        Next = Current->next ;
        if ( !Next || Next == Tail) break ; // means list has been traversed fully : Most Important
        Current->prev->next = Next ;
        Next->prev = Current->prev ;
        Current->next= Current->prev = 0 ;
        Tail->next = Current ;
        Current->prev = Tail ;
        Tail = Current ;
        Current = Next ;
        Tail->next = 0 ;

    }
}
}

int main ()
{
    Doubly D ;
    D.Creation();
    D.Display();
    D.Sort123();
    D.Display();
}
