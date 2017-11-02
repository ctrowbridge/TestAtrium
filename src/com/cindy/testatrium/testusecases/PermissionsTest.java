package com.cindy.testatrium.testusecases;

import com.cindy.testatrium.testcases.AtriumBaseTestCase;

/**
 * Source:  https://www.drupal.org/node/2752303
 * 
 * Public space with a public Discussion section. Configure write permission
 * managed via Group membership and not via direct space membership.
 * 
 * Permissions
 * 
 * READ access
 * Since the space is public and the section is public, every visitor will have READ access to the
 * space and the content in the public sections.
 * 
 * WRITE access
 * In order to configure write access via group membership, follow the steps below:
 * 
 * Step 1: Create group
 *
 *  Create a new group and select "Child's permissions - inherited users in this group's
 *  subgroups will have the permissions of a generic member of that subgroup." in the
 *  "Group user permission inheritance" paragraph.
 *  
 * Step 2: Add user(s)
 * 
 * Add the users you want to have write access to this group.
 * 
 * Step 3: Add group to space
 *
 * Go to the space and add the group to the members list.
 * 
 * @author Cindy
 */
public class PermissionsTest extends AtriumBaseTestCase {

}
